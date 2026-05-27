const expressApp = require('express');
const dbConexion = require('./config/db');
const rutasFestivos = require('./config/routes/festivoRoutes');
const uiSwagger = require('swagger-ui-express');

const app = expressApp();

// Conectar DB
dbConexion();

// Configuración de Swagger Manual (JSON)
const docFestivos = {
    openapi: '3.0.0',
    info: {
        title: 'API Festivos Colombia',
        version: '1.0.0'
    },
    servers: [{ url: 'http://localhost:3000' }],
    paths: {
        '/api/festivos/verificar/{year}/{month}/{day}': {
            get: {
                summary: 'Verifica un festivo',
                parameters: [
                    { name: 'year', in: 'path', required: true, schema: { type: 'integer' } },
                    { name: 'month', in: 'path', required: true, schema: { type: 'integer' } },
                    { name: 'day', in: 'path', required: true, schema: { type: 'integer' } }
                ],
                responses: { 200: { description: 'OK' } }
            }
        },
        '/api/festivos/obtener/{year}': {
            get: {
                summary: 'Listar festivos del año',
                parameters: [
                    { name: 'year', in: 'path', required: true, schema: { type: 'integer' } }
                ],
                responses: { 200: { description: 'OK' } }
            }
        }
    }
};

app.use(expressApp.json());

// Servir Documentación
app.use('/api-docs', uiSwagger.serve, uiSwagger.setup(docFestivos));

// Usar Rutas
app.use('/api/festivos', rutasFestivos);

app.listen(3000, () => {
    console.log("✅ Servidor en puerto 3000");
    console.log("📖 Swagger: http://localhost:3000/api-docs");
});