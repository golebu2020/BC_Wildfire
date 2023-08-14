<template>
  <div>
    <Map />
  </div>
</template>

<script>
import Map from './components/Map.vue'

export default {
  name: 'App',
  mounted(){
    console.log("Thanks")
    console.log(process.env.VUE_APP_API_KEY)
  },
  components: {
    Map
  },
  methods:{
  
  }
}
</script>
