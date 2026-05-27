const mongoose = require('mongoose');
const Festivo = require('./models/festivo');

const conectar = async () => {
    await mongoose.connect('mongodb://localhost:27017/db_festivos');
    console.log('✅ Conectado a MongoDB');
};

const datos = [
    // Tipo 1 - Fijos
    { nombre: 'Año nuevo',               dia: 1,  mes: 1,  diasPascua: 0, idTipo: 1 },
    { nombre: 'Día del Trabajo',         dia: 1,  mes: 5,  diasPascua: 0, idTipo: 1 },
    { nombre: 'Independencia Colombia',  dia: 20, mes: 7,  diasPascua: 0, idTipo: 1 },
    { nombre: 'Batalla de Boyacá',       dia: 7,  mes: 8,  diasPascua: 0, idTipo: 1 },
    { nombre: 'Inmaculada Concepción',   dia: 8,  mes: 12, diasPascua: 0, idTipo: 1 },
    { nombre: 'Navidad',                 dia: 25, mes: 12, diasPascua: 0, idTipo: 1 },

    // Tipo 2 - Ley de Puente festivo
    { nombre: 'Santos Reyes',                  dia: 6,  mes: 1,  diasPascua: 0, idTipo: 2 },
    { nombre: 'San José',                      dia: 19, mes: 3,  diasPascua: 0, idTipo: 2 },
    { nombre: 'San Pedro y San Pablo',         dia: 29, mes: 6,  diasPascua: 0, idTipo: 2 },
    { nombre: 'Asunción de la Virgen',         dia: 15, mes: 8,  diasPascua: 0, idTipo: 2 },
    { nombre: 'Día de la Raza',                dia: 12, mes: 10, diasPascua: 0, idTipo: 2 },
    { nombre: 'Todos los santos',              dia: 1,  mes: 11, diasPascua: 0, idTipo: 2 },
    { nombre: 'Independencia de Cartagena',    dia: 11, mes: 11, diasPascua: 0, idTipo: 2 },

    // Tipo 3 - Basados en Pascua sin traslado
    { nombre: 'Jueves Santo',      dia: 0, mes: 0, diasPascua: -3, idTipo: 3 },
    { nombre: 'Viernes Santo',     dia: 0, mes: 0, diasPascua: -2, idTipo: 3 },
    { nombre: 'Domingo de Pascua', dia: 0, mes: 0, diasPascua:  0, idTipo: 3 },

    // Tipo 4 - Basados en Pascua con traslado a lunes
    { nombre: 'Ascensión del Señor',        dia: 0, mes: 0, diasPascua: 40, idTipo: 4 },
    { nombre: 'Corpus Christi',             dia: 0, mes: 0, diasPascua: 61, idTipo: 4 },
    { nombre: 'Sagrado Corazón de Jesús',   dia: 0, mes: 0, diasPascua: 68, idTipo: 4 },
];

const sembrar = async () => {
    console.log('🚀 Iniciando seed...');
    try {
        await conectar();
        console.log('🗑️ Limpiando colección...');
        await Festivo.deleteMany({});
        console.log('🗑️ Colección limpiada');
        await Festivo.insertMany(datos);
        console.log('✅ Festivos insertados correctamente');
    } catch (error) {
        console.error('❌ Error:', error.message);
        console.error(error);
    } finally {
        await mongoose.disconnect();
        console.log('🔌 Desconectado');
    }
};

sembrar();