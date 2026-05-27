const mongoose = require('mongoose');

const conectarDB = async () => {
    try {
        const uri = process.env.MONGO_URI || 'mongodb://localhost:27017/db_festivos';
        await mongoose.connect(uri);
        console.log('✅ MongoDB Conectado exitosamente');
    } catch (error) {
        console.error('❌ Error de conexión:', error.message);
        process.exit(1);
    }
};

module.exports = conectarDB;
