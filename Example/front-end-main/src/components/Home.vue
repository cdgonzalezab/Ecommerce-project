<template>

<div class="body">
  <div class="card1">
    <div class="card_part card_part-one">
    </div>
    <!-- Photo 2 -->
    <div class="card_part card_part-two">
    </div>
    <!-- Photo 3 -->
    <div class="card_part card_part-three">
    </div>
    <!-- Photo 4 -->
    <div class="card_part card_part-four">
    </div>
  </div> 
    <br/>
    <div class="cuerpo">
      <h1>GUARDERÍA CANINOS FELICES</h1>
      <p>
        Un proyecto creado con la intensión de prestar un servicio amigable, que
        no solo busca resolver los problemas de salud, brindar un excelente
        producto para el entretenimiento de ellos sino también en los métodos
        complementarios para el bienestar de las mascotas del sector, por lo que
        hemos implementado una tienda virtual pensando en nuestras queridas
        mascota. Encontrarás diferentes servicios como consulta general,
        ecografía, rayos x, cirugía, profilaxis, entre otros. Y en nuestra
        increíble tienda podrás encontrar medicamentos para el cuidado de la
        salud de tu peludo, cuidado corporal como cremas, cepillos, jabones,
        colonias y complementos, además contamos con productos la diversión y
        felicidad de tu mascota. ¡Los esperamos!
      </p>
    </div>
</div>
<br>
</template> 


<script>
export default {
  name: "home",
};
</script>


<style>
  .body {

    margin: 0;
    padding: 0;
    width: 100%;
    height: 100;
    display: grid;
    min-height: 100vh;

    justify-content: center;
    justify-items: center;
    line-height: 1.5;
    font-family: -apple-system, BlinkMacSystemFont, avenir next, avenir,
      helvetica neue, helvetica, Ubuntu, roboto, noto, segoe ui, arial, sans-serif;
    color: #444;
    background-color: #ffffff;
    
    
    overflow: hidden;
    animation: spin var(--duration) var(--delay) linear infinite;
    transform-style: preserve-3d;
    perspective: 25rem;
    
  }

  .cuerpo {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    padding-left: 15%;
    padding-right: 15% ;
  }
.cuerpo h1 {
  font-family: "Franklin Gothic Medium", "Arial Narrow", Arial, sans-serif;
  margin-top: 2rem;
}
.cuerpo p {
  text-align: justify;
  }

  img {
    border-radius: 0.5rem 0.5rem 0 0;
    width: 100%;
    object-fit: cover;

    max-height: max(20rem, 30vh);
    aspect-ratio: 4/3;
    mix-blend-mode: var(--card-blend-mode);
  }

  :last-child {
    margin-bottom: 0;
  }

  :hover,
  :focus-within {
    --card-gradient: #00000075 max(8.5rem, 20vh);
  }

/* Additional demo display styles */
  * {
    box-sizing: border-box;
  }

  .card h3 {
    margin-top: 1rem;
    font-size: 1.25rem;
  }

  .card a {
    color: inherit;
  }

  .card-wrapper {
    list-style: none;
    padding: 0;
    margin: 0;
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(30ch, 1fr));
    grid-gap: 1.5rem;
    max-width: 100vw;
    width: 120ch;
    padding-left: 1rem;
    padding-right: 1rem;
  }

  :root {
    --duration: 20s;
    --delay: 20s;
  }

  html {
    background-color: black;
    animation: shrink var(--duration) var(--delay) linear infinite;
  }

  .card1 {
    position: relative;

    width: 700px;
    height: 400px;
    overflow: hidden;

    border-radius: 5px;
    box-shadow: 0px 4px 10px rgba(0, 0, 0, 0.3);
  }

  /*Light blue cover above the slide show*/
  .card1::after {
    content: "";
    position: absolute;
    left: 0;
    top: 0;
    z-index: 900;

    display: block;
    width: 100%;
    height: 100%;

    background-color: rgba(140, 22, 115, 0.2);
  }

  .card_part {
    position: absolute;
    top: 0;
    left: 0;
    z-index: 7;

    display: flex;
    align-items: center;
    width: 100%;
    height: 100%;

    transform: translateX(700px);
    background-image: url(https://images.unsplash.com/photo-1544568100-847a948585b9?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ);

    animation: opaqTransition 28s cubic-bezier(0, 0, 0, 0.97) infinite;
  }

  .card_part.card_part-two {
    z-index: 6;
    background-image: url(https://images.unsplash.com/photo-1517423440428-a5a00ad493e8?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ);
    animation-delay: 7s;
  }

  .card_part.card_part-three {
    z-index: 5;
    background-image: url(https://images.unsplash.com/photo-1510771463146-e89e6e86560e?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ);
    animation-delay: 14s;
  }

  .card_part.card_part-four {
    z-index: 4;
    background-image: url(https://images.unsplash.com/photo-1507146426996-ef05306b995a?ixlib=rb-1.2.1&q=80&fm=jpg&crop=entropy&cs=tinysrgb&w=400&fit=max&ixid=eyJhcHBfaWQiOjE0NTg5fQ);
    animation-delay: 21s;
  }

  @keyframes opaqTransition {
    3% {
      transform: translateX(0);
    }
    25% {
      transform: translateX(0);
    }
    28% {
      transform: translateX(-700px);
    }
    100% {
      transform: translateX(-700px);
    }
  }
  
</style>