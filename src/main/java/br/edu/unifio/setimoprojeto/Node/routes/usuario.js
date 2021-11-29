const express = require("express")
const router = express.Router()

router.get('/',(req, res) => {
    res.render()
})

router.get('/cad',(req, res) => {
    res.render('formulario')
})

module.exports = router