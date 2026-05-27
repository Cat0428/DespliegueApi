const swaggerJsdoc = require('swagger-jsdoc');

const options = {
  definition: {
    openapi: '3.0.0',
    info: {
      title: 'API de Festivos Colombia',
      version: '1.0.0',
      description: 'Microservicio para validar fechas festivas basado en Express y MongoDB',
    },
    servers: [
      {
        url: 'http://localhost:8080',
      },
    ],
  },
  apis: ['./src/config/routes/*.js'], // Ruta donde están tus archivos de rutas para leer la documentación
};

const specs = swaggerJsdoc(options);
module.exports = specs;