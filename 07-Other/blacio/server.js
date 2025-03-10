const port = 3005;
const express = require('express');
const mongoose = require('mongoose');
const app = express();

mongoose.connect('mongodb+srv://SrJCBM:OOP14539ODS@firstdb.gtv30gi.mongodb.net/strategyBlacio', {
    useNewUrlParser: true,
    useUnifiedTopology: true
});

const db = mongoose.connection;
db.on('error', (error) => console.error(error));
db.once('open', () => console.log('Connected to MongoDB Database'));

app.use(express.json());
app.use(express.static('public')); // Serve the frontend files

// Importing routes
const sortRoutes = require('./routes/sortRoutes.js');
app.use('/sort', sortRoutes);

app.listen(port, () => {
    console.log("Sorting Application Server is Running on port --> " + port);
});
