<template>
  <div class="parent-container">
    
      <div class="map-container">
        <GmapMap :center="{ lat: 53.2833330, lng: -123.1333330 }"  :zoom="8" style="width: 80vw; height: 100%;" >
          <GmapMarker v-for="(location, index) in retrievedLocations" :key="index" :position="location" />
          <div v-if="retrievedLocations.length=0">Loading...</div>
        </GmapMap>
      </div>
    
  
   
    <Sidebar @filterData="triggerFilterSelected" />
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
      retrievedLocations:[],
    };
  },
  methods:{
    fetchAllData(){
      axios.get('http://127.0.0.1:8000/api/wildfire/search/')
        .then(response => {
          const feature_list = response.data.features
          feature_list.forEach(element => {
            this.retrievedLocations.push({
              lat: element.properties.LATITUDE,
              lng: element.properties.LONGITUDE
            })
          });
              
        })
        .catch(error => {
          console.error('Error fetching data:', error.message);
        });
    },  
    triggerFilterSelected(link){
      this.retrievedLocations = []
      axios.get(link)
        .then(response => {
          const feature_list = response.data
          feature_list.forEach(element => {
            this.retrievedLocations.push({
              lat: element.properties.LATITUDE,
              lng: element.properties.LONGITUDE
            })
          });
              
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
