<template>
  <div class="parent-container">
    <div class="map-container">
      <GmapMap :center="{ lat: 48.4701, lng: -123.4667 }"  :zoom="5" style="width: 80vw; height: 100%;" >
        <GmapMarker v-for="(location, index) in locations" :key="index" :position="location" />
      </GmapMap>
    </div>
    <Sidebar/>
  </div>
</template>

<script>

import Sidebar from './Sidebar.vue';
import axios from 'axios';

export default {
  components: {
    Sidebar,
  },
  data() {
    return {
      locations: [
        { lat: 48.4701, lng: -123.4667 },
        { lat: 48.4067, lng: -123.5143 },
        { lat: 49.2686, lng: -125.17 },

      ],
      retrievedLocation:[],
    };
  },
  methods:{
    fetchAllData(){
      axios.get('http://127.0.0.1:8000/api/wildfire/search/')
        .then(response => {
          this.retrievedLocation.append({
            lat: response.data.features.properties.LATITUDE,
            lng: response.data.features.properties.LONGITUDE
          }); 
          console.log(this.retrievedLocation)       
        })
        .catch(error => {
          console.error('Error fetching data:', error.message);
        });
    }
  
   
  },
  mounted(){
    console.log("Just Mounted")
    this.fetchAllData()
  }
};
</script>

<style>
.parent-container {
  display: flex;
}
.map-container {
  background: blue;
}

.parent-container {
  background: #fff;
}


</style>
