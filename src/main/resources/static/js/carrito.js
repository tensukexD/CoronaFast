
var subtotal = document.getElementById("precioSubTotal");
var envio = document.getElementById("precioEnvio");
var total = document.getElementById("precioTotal");
var botonActualizar = document.getElementById("botonActualizar");
var inputdeltotal = document.getElementById("escondido");


var comprador;
var arregloProductos = [];
var productosCarrito = [];
var arregloCarrito = [];
var arregloPrecioTotal = [];
var entrar = true;
var precioTotal = null;
var cantidad = null;
var subTotalNumero = 0;
var precioEnvio = 20;
var precioFinal = 0;


var url = "http://localhost:8080/revisarCompradores/compradores/seleccionar/"+id.toString();
var xhr = new XMLHttpRequest();

function obtenerComprador(){
    xhr.onload = function(){
        comprador = JSON.parse(xhr.response);
        arregloCarrito = comprador.carritoCompras;
        console.log(comprador.carritoCompras[0]);
        for(let i=0;i<comprador.carritoCompras.length; i++){
            console.log(arregloCarrito[i]);
            
        }
        obtenerProductos()
    }
    xhr.open('GET', url,true);
    xhr.send();
}



var xhr2 = new XMLHttpRequest();

function obtenerProductos(){
    var productos = [];
        var url2 = "http://localhost:8080/revisarProductos/productos/mostrar/";
        xhr2.onload = function(){
            productos = JSON.parse(xhr2.response);
            arregloProductos = productos;
            for(let i=0;i<arregloProductos.length;i++){
                console.log(arregloProductos[i].idProducto);
            }
            var producto;
            for(let i=0; i<comprador.carritoCompras.length;i++){
                for(let j=0; j<productos.length;j++){
                    if(arregloCarrito[i] == arregloProductos[j].idProducto){
                        console.log(comprador.carritoCompras[i].toString());
                        console.log(productos[j].idProducto.toString());
                        producto = productos[j]
                        productosCarrito.push(producto);
                    }
                }
            }
            console.log(productosCarrito);
            agregarPrecios();
        }
        xhr2.open('GET', url2,true);
        xhr2.send();
        
}

function agregarPrecios(){
    subTotalNumero = 0;
    precioFinal = 0;
    for(let i=0; i<arregloCarrito.length;i++){
        cantidad = document.getElementById("cantidadProducto"+arregloCarrito[i].toString());
        precioTotal = document.getElementById("precioProductoTotal"+arregloCarrito[i].toString());
        console.log(cantidad.value);
        console.log(precioTotal.value);
        subTotalNumero+=(parseFloat(cantidad.value)*parseFloat(productosCarrito[i].precio));
        precioTotal.innerText = "$"+(parseFloat(cantidad.value)*parseFloat(productosCarrito[i].precio)).toString();
    }
    precioFinal=subTotalNumero+precioEnvio;
    console.log(precioFinal);
    subtotal.innerText = subTotalNumero.toString();
    envio.innerText = precioEnvio.toString();
    total.innerText = precioFinal.toString();
    inputdeltotal.value=precioFinal.toString();
}



window.onload = function(){
    obtenerComprador();
    
}

