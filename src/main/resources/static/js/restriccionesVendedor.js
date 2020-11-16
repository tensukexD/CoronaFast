

var estadoEmail = false;
var estadoRUC = false;
var arregloVendedores = [];
var arregloCompradores = [];
var arregloTiendas = [];

var url = "http://coronafast.herokuapp.com/revisarVendedores/vendedores/mostrar/";
var url2 = "http://coronafast.herokuapp.com/revisarTienda/tiendas/mostrar/";
var url3 = "http://coronafast.herokuapp.com/revisarCompradores/compradores/mostrar";

var xhr = new XMLHttpRequest();
var xhr2 = new XMLHttpRequest();
var xhr3 = new XMLHttpRequest();

var alertaEmail = document.getElementById("alertaEmail");
var alertaRUC = document.getElementById("alertaRUC");
var botonCrear = document.getElementById("botonVendedor");

function btnActivo(){
    if(estadoEmail == true && estadoRUC == true){
        botonCrear.disabled = false;
    }else{
        botonCrear.disabled = true;
    }
}

function obtenerVendedores(){
    xhr.onload = function(){
        arregloVendedores = JSON.parse(xhr.response);
        console.log(JSON.parse(xhr.response))
        console.log(arregloVendedores[0].email);
    }
    xhr.open('GET', url,true);
    xhr.send();
}

function obtenerCompradores(){
    xhr3.onload = function(){
        arregloCompradores = JSON.parse(xhr3.response);
        console.log(arregloCompradores[0].email);
    }
    xhr3.open('GET', url3,true);
    xhr3.send();
}

function obtenerTiendas(){
    xhr2.onload = function(){
        var arregloJSON = JSON.parse(xhr2.response);
        arregloTiendas = arregloJSON;
        console.log(arregloTiendas[0].ruc);
    }
    xhr2.open('GET', url2,true);
    xhr2.send();
}

var email = document.getElementById("email");
var ruc = document.getElementById("ruc");

email.addEventListener("input",function(){
    if(email.value==""){
        alertaEmail.innerHTML = "<strong>Deber llenar este campo obligatoriamente</strong>";
        alertaEmail.style.display = "block";
        estadoEmail = false;
        btnActivo();
    }else{
        estadoEmail = true;
        alertaEmail.style.display = "none";
        if(arregloVendedores != [] || arregloCompradores != []){
            for(var i=0;i<arregloVendedores.length;i++){
                if(email.value==arregloVendedores[i].email){
                    alertaEmail.innerHTML = "<strong>La entidad ya se ha creado</strong>";
                    alertaEmail.style.display = "block";
                    estadoEmail = false;
                }
            }
            for(var j=0;j<arregloCompradores.length;j++){
                if(email.value==arregloCompradores[j].email){
                    alertaEmail.innerHTML = "<strong>La entidad ya se ha creado</strong>";
                    alertaEmail.style.display = "block";
                    estadoEmail = false;
                }
            }
        }
        btnActivo();
    }
})

ruc.addEventListener("input",function(){
    if(ruc.value==""){
        alertaRUC.innerHTML = "<strong>Deber llenar este campo obligatoriamente</strong>";
        alertaRUC.style.display = "block";
        estadoRUC = false;
        btnActivo();
    }else{
        estadoRUC = true;
        alertaRUC.style.display = "none";
        if(arregloTiendas !=[]){
            for(var i=0;i<arregloTiendas.length;i++){
                if(ruc.value==arregloTiendas[i].ruc){
                    alertaRUC.innerHTML = "<strong>La entidad ya se ha creado</strong>";
                    alertaRUC.style.display = "block";
                    estadoRUC = false;
                }
            }
        }
        btnActivo();
    }
})



window.onload = function(){
    obtenerVendedores();
    obtenerCompradores();
    obtenerTiendas();
}