const db = require('./db') // O ./ significa q o arquivo esta na mesma pasta

const Post = db.sequelize.define('postagens', { //'postagens' = nome da tabela
    titulo: {
        type: db.Sequelize.STRING,
        allowNull: false,
        validate: {//procurar sobre validation do sequelize
            notEmpty: {
                msg: "Esse campo não pode ser vazio"
            },
            len: {
                args: [4, 20],
                msg: "Esse campo deve ter entre 4 e 20 caracteres"
            }
        }
    },
    conteudo: {
        type: db.Sequelize.TEXT,
        allowNull: false,
        validate: {
            notEmpty: {
                msg: "Esse campo não pode ser vazio"
            },
            len: {
                args: [4, 500],
                msg: "Esse campo deve ter entre 4 e 500 caracteres"
            }
        }
    }
})

//Post.sync({force: true})
module.exports = Post