const mongoose = require('mongoose');

const festivoSchema = new mongoose.Schema({
nombre: {type: String, required: true},
dia: {type: Number, required: true},
mes: {type: Number, required: true},
diasPascua: {type: Number, default: 0},
idTipo: {type: Number, required: true},
});

module.exports = mongoose.model('Festivo', festivoSchema);