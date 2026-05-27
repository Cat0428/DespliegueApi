// 1. Declaramos la función de Pascua
const obtenerDomingoPascua = (year) => {
    const a = year % 19;
    const b = Math.floor(year / 100);
    const c = year % 100;
    const d = Math.floor(b / 4);
    const e = b % 4;
    const f = Math.floor((b + 8) / 25);
    const g = Math.floor((b - f + 1) / 3);
    const h = (19 * a + b - d - g + 15) % 30;
    const i = Math.floor(c / 4);
    const k = c % 4;
    const l = (32 + 2 * e + 2 * i - h - k) % 7;
    const m = Math.floor((a + 11 * h + 22 * l) / 451);
    
    const mes = Math.floor((h + l - 7 * m + 114) / 31);
    const dia = ((h + l - 7 * m + 114) % 31) + 1;

    return new Date(year, mes - 1, dia);
};

// 2. Declaramos la función de traslado (Ley Emiliani)
const trasladarALunes = (fecha) => {
    // IMPORTANTE: Trabajamos con una copia para no alterar la original por referencia
    const res = new Date(fecha);
    const diaSemana = res.getDay(); // 0 = Domingo, 1 = Lunes...

    if (diaSemana !== 1) { // Si no es lunes
        const diferencia = (diaSemana === 0) ? 1 : (8 - diaSemana);
        res.setDate(res.getDate() + diferencia);
    }
    return res;
};

// 3. EXPORTAMOS AMBAS (Esto es lo que el controlador necesita)
module.exports = { 
    obtenerDomingoPascua, 
    trasladarALunes 
};