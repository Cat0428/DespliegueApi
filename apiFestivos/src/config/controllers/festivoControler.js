const festivo = require('../models/festivo');
const Festivo = require('../models/festivo');
const { obtenerDomingoPascua, trasladarALunes } = require('../utils/calculadoraFechas');

exports.verificarFestivo = async (req, res) => {
    try {
        const { year, month, day } = req.params;
        const y = parseInt(year);
        const m = parseInt(month);
        const d = parseInt(day);

        const fechaConsulta = new Date(y, m - 1, d);
        if (isNaN(fechaConsulta.getTime()) || fechaConsulta.getMonth() + 1 !== m) {
            return res.status(400).json({ error: 'Fecha inválida' });
        }

        const festivos = await Festivo.find();
        const domingoPascua = obtenerDomingoPascua(y);
        let esFestivo = false;

        for (const f of festivos) {
            let fechaFestivo = null;

            // Validación de seguridad: Si no hay idTipo o datos base, saltar este registro
            if (!f.idTipo) continue;

            switch (f.idTipo) {
                case 1: // Fijo
                    if (f.mes && f.dia) fechaFestivo = new Date(y, f.mes - 1, f.dia);
                    break;
                case 2: // Fijo traslada a lunes
                    if (f.mes && f.dia) fechaFestivo = trasladarALunes(new Date(y, f.mes - 1, f.dia));
                    break;
                case 3: // Pascua (No traslada)
                    fechaFestivo = new Date(domingoPascua);
                    fechaFestivo.setDate(fechaFestivo.getDate() + (parseInt(f.diasPascua) || 0));
                    break;
                case 4: // Pascua traslada a lunes
                    fechaFestivo = new Date(domingoPascua);
                    fechaFestivo.setDate(fechaFestivo.getDate() + (parseInt(f.diasPascua) || 0));
                    fechaFestivo = trasladarALunes(fechaFestivo);
                    break;
            }

            // Validar que la fecha generada sea válida antes de comparar
            if (fechaFestivo && !isNaN(fechaFestivo.getTime())) {
                if (fechaFestivo.getDate() === d && fechaFestivo.getMonth() + 1 === m) {
                    esFestivo = true;
                    break;
                }
            }
        }
        res.send(esFestivo ? 'Es festivo' : 'No es festivo');

    } catch (error) {
        console.error("DETALLE DEL ERROR:", error); // Esto te dirá exactamente qué falló en la consola
        res.status(500).send('Error interno al verificar');
    }
};

exports.obtenerFestivosAnio = async (req, res) => {
    try {
        const { year } = req.params;
        const y = parseInt(year);

        if (isNaN(y) || y < 1900 || y > 2100) {
            return res.status(400).json({ error: "Año no válido" });
        }

        const festivosDB = await Festivo.find();
        const domingoPascua = obtenerDomingoPascua(y);
        
        const listaFestivos = [];

        for (const f of festivosDB) {
            let fechaFestivo = null;

            switch (f.idTipo) {
                case 1:
                    fechaFestivo = new Date(y, f.mes - 1, f.dia);
                    break;
                case 2:
                    fechaFestivo = trasladarALunes(new Date(y, f.mes - 1, f.dia));
                    break;
                case 3:
                    fechaFestivo = new Date(domingoPascua);
                    fechaFestivo.setDate(fechaFestivo.getDate() + (parseInt(f.diasPascua) || 0));
                    break;
                case 4:
                    fechaFestivo = new Date(domingoPascua);
                    fechaFestivo.setDate(fechaFestivo.getDate() + (parseInt(f.diasPascua) || 0));
                    fechaFestivo = trasladarALunes(fechaFestivo);
                    break;
            }

            if (fechaFestivo && !isNaN(fechaFestivo.getTime())) {
                listaFestivos.push({
                    festivo: f.nombre,
                    fecha: fechaFestivo.toISOString().split('T')[0]
                });
            }
        }

        listaFestivos.sort((a, b) => a.fecha.localeCompare(b.fecha));
        res.json(listaFestivos);

    } catch (error) {
        console.error("DETALLE DEL ERROR:", error);
        res.status(500).json({ error: "Error al generar listado" });
    }
};