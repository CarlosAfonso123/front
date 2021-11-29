const Sequelize = require('sequelize')
//COnexão com o banco ->
const sequelize = new Sequelize('postapp', 'root', 'Th0rCBP77_', {
    host: "localhost",
    dialect: 'mysql'
})

module.exports = {
    Sequelize: Sequelize,
    sequelize: sequelize
}