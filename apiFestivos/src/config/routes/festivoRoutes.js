const express = require('express');
const router = express.Router();
const festivoController = require('../controllers/festivoControler');

// Rutas limpias
router.get('/verificar/:year/:month/:day', festivoController.verificarFestivo);
router.get('/obtener/:year', festivoController.obtenerFestivosAnio);

module.exports = router;