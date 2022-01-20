<template>
  <div class="abus">
    <div class="cont">
      <h1>CONÓCENOS</h1>
      <p>
        El proyecto GUARDERÍA CANINOS FELICES fue creada con la intensión de una
        propuesta de negocio que se trata de una guardería especializada para
        perros donde estos recibirán un cuidado y una enseñanza básica en caso
        que los dueños por algún motivo no puedan cuidar de ellos. También
        contamos con una tienda virtual de productos. Esta guardería cuenta con
        cámaras en todos los ambientes que permitirán poder observar a tu
        mascota en tiempo real desde donde te encuentres y de esta manera puedes
        saber cómo esta, además de asegurarte de que reciba un buen cuidado y
        todo lo necesario para su bienestar.
      </p>
    </div>

    <div class="cont">
      <h1>MISIÓN</h1>
      <p>
        “Lograr posicionarse en el mercado y ser la mejor tienda virtual
        reconocido en el medio promoviendo el bienestar animal mediante, la
        prestación de nuestros servicios de cuidado médicos veterinarios y
        complementarios con los mejores productos para un excelente bienestar y
        fomentar en las nuevas generaciones el gusto por este maravilloso
        cuidado sobre sus animales”
      </p>
    </div>

    <div class="cont">
      <h1>VISIÓN</h1>
      <p>
        Ser una guardería líder y reconocida a nivel nacional como la mejor tienda virtual,
         en la promoción del bienestar animal mediante nuestros servicios de primera calidad 
         y tecnología.
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AboutUs',
  setup() {
    
  },
}
</script>

<style>

.about-us {
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 100%;
  padding: 1rem;
}
.cont h1 {
  margin-top: 1rem;
  display: flex;
  justify-content: center;
}
.cont p {
  text-align: justify;
  margin-top: 1rem;
  margin-bottom: 2rem;
}

</style>
