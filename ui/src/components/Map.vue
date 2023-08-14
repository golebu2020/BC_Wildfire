<template>
  <div class="parent-container">
    
      <div class="map-container">
        <!-- <div>Heys</div> -->
        <GmapMap :center="{ lat: 53.2833330, lng: -123.1333330 }"  :zoom="8" style="width: 80vw; height: 100%;" >
          <GmapMarker @click="handleMarkerClick(index)" v-for="(location, index) in retrievedLocations" :key="index" :position="location" />
        </GmapMap>
        <div v-if="filterName != ''">
          <div class="floating-div border-design">
            <div class="filter__title">{{filterQuery}}:</div>
            <hr>
            {{filterName}}
          </div>
        </div>

        <div v-if="isSelected">
          <DisplayFireInfo :capture="capturedInfo" @close="closeModal"/>
        </div>
        <div v-else></div>
        
       </div>
    
      <Sidebar @filterData="triggerFilterSelected" />
    </div>
    
  
</template>

<script>

import Sidebar from './Sidebar.vue';
import DisplayFireInfo from './DisplayFireInfo.vue';
import axios from 'axios';


export default {
  components: {
    Sidebar,
    DisplayFireInfo,
    
  },
  data() {
    return {
      retrievedLocations:[],
      filterName:"",
      filterQuery:"",
      feature_list:null,
      capturedInfo:{},
      isSelected:false,
    };
  },
  methods:{
    fetchAllData(){
      axios.get('http://127.0.0.1:8000/api/wildfire/search/')
        .then(response => {
          this.feature_list = response.data.features
          this.feature_list.forEach(element => {
            this.retrievedLocations.push({
              lat: element.properties.LATITUDE,
              lng: element.properties.LONGITUDE
            })
          });
              
        })
        .catch(error => {
          console.error(error.message);
        });
    },  
    triggerFilterSelected(link,fName,fQuery){
      this.retrievedLocations = []
      this.filterName = fName
      this.filterQuery = fQuery.replace(/_/g, " ").toLowerCase().split(' ').map(word => word.charAt(0).toUpperCase() + word.slice(1))
    .join(' ');
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
          console.error(error.message);
        });
    },
    handleMarkerClick(index){
      this.capturedInfo = this.feature_list[index].properties
      this.isSelected = true
      
    },
    closeModal(){
      this.isSelected = false
    }
   
  },
  mounted(){
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

.parent-container {
  position: relative;
}

.filter__title{
  color: #000000;
  font-weight: bold;
}

hr{
  opacity: 0.2;
  margin-bottom: 5px;
}

</style>
