var botonTiendaFav, botonProdFav, botonCarrito;
      botonTiendaFav = document.getElementById("botonTiendaFav");
      botonProdFav = document.getElementById("botonProdFav");
      botonCarrito = document.getElementById("botonCarrito");

      botonTiendaFav.addEventListener("click", function(){
        var xhr = new XMLHttpRequest();
        var url = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+idUsuario+"/agregarTienda/"+idTienda;
        xhr.open('PUT', url,true);
        xhr.send();
      });

      botonProdFav.addEventListener("click", function(){
        var xhr = new XMLHttpRequest();
        var url = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+idUsuario+"/agregarProducto/"+idProducto;
        xhr.open('PUT', url, true);
        xhr.send();
      });

      botonCarrito.addEventListener("click", function(){
        var xhr = new XMLHttpRequest();
        var url = "http://coronafast.herokuapp.com/revisarCompradores/compradores/"+idUsuario+"/agregarCarrito/"+idProducto;
        xhr.open('PUT', url, true);
        xhr.send();
      });