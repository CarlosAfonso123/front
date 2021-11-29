const bodyParser = require("body-parser");
const express = require("express")
const app = express();
const handlebars = require('express-handlebars')
const Sequelize = require('sequelize')
const Post = require("./models/Post")
const usuario = require("./routes/usuario")
const path = require("path")

// Config
    // Template ENgine                                     //main.handlebars é o html
        app.engine('handlebars', handlebars({defaultLayout: 'main'}))
        app.set('view engine', 'handlebars')
        //BODY PARSER
        app.use(bodyParser.urlencoded({extended: false}))
        app.use(bodyParser.json())
        
//ROTAS
    app.get('/', function(req, res){
        Post.findOne({order: [['id', 'DESC']]}).then(function(posts){
            console.log(posts)
            res.render('home', {posts: posts})
        })
    })

    /*app.get("/", (req, res) => {
        //Buscar no banco de dados todos os artigos usando o find
       Post.find({}).then((post) => {
           //retornar os artigos para o aplicativo que fez a requisição
            return res.json(post);
        }).catch((erro) => {
            //Retornar erro ao aplicativo que fez a requisição informando que não encontrou nenhum artigo
            return res.status(400).json({
                error: true,
                message: "Nenhum artigo encontrado!"
            })
        })
    });*/


                           //pega os dados da requisição
    app.get('/cad', function(req, res){
        res.render('formulario')
    })

    //MiddleWare
    app.use((req, res, next) => {
        console.log("EU sou o MIDLLE")
        next()
    })
    //app.use('/cad', usuario)//?

    app.post('/add', function(req, res){
        Post.create({
            titulo: req.body.titulo,
            conteudo: req.body.conteudo
        }).then(function(){ 
            res.redirect('/')
        }).catch(function(erro){
            res.send("Houve um erro: " + erro)
        })
        //res.send("Texto: "+req.body.titulo+ " Conteudo: "+req.body.conteudo)
    })

    app.get('/deletar/:id', function(req, res){
        //Post.destroy({where: {'id': req.params.id, 'nome': 'victor'}} )
        Post.destroy({where: {'id': req.params.id}}).then(function(){
            res.send("Postagem deletada com sucesso!")
        }).catch(function(erro){
            res.send("Esta postagem não existe!")
        })
    })

    app.get('/postagens/editar/:id', function(req,res) {
        res.render("editpostagens")
    })

    //PUBLIC
    app.use(express.static(path.join(__dirname, "public")))

app.listen(8081, function(){ // function é o callback
    console.log("Servidor rodando url http://localhost:8081")
})

//npm install nodemon -g       -g é globalmente...     
//npm install sequelize --save
//npm install --save mysql2
//npm install --save express-handlebars
//npm install --save body-parser