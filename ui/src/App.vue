<template>
  <div>
    <Map />
  </div>
</template>

<script>
import Map from "./components/Map.vue";

export default {
  name: "App",
  mounted() {
    console.log("Thanks");
    console.log(process.env.VUE_APP_API_KEY);
  },
  components: {
    Map,
  },
  methods: {},
};
</script>
<style>
.floating-div {
  height: 40px;
  width: fit-content;
  position: absolute;
  background: #fff;
  border-radius: 5px;
  top: 0;
  left: 0;
  transform: translateX(700px);
  z-index: 1;
}

.floating-div-display-info {
  height: fit-content;
  width: fit-content;
  position: absolute;
  background: #fff;
  border-radius: 5px;
  top: 200px;
  left: -255px;
  transform: translateX(300px);
  z-index: 1;
}

.border-design {
  margin: 2px;
  box-shadow: 1px 1px 3px 0.4px rgba(38, 38, 38, 0.459);
  border-radius: 2px;
  padding: 10px;
  margin-bottom: 5px;
  margin-left: 5px;
}
hr {
  opacity: 0.2;
  margin-bottom: 5px;
}
</style>
