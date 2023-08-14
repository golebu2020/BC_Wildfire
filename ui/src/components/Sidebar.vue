<template>
    <div class="filter-container">
        <div class="title-container">
          <h4>Filter BC Wildfire from 2023.</h4>

        </div>
        <div class="fire-status border-design">
            
            <p>Fire Cause</p>
            <hr>
            <div v-if="fireStatus.length>0" >
                <ul v-for="(item1, index) in fireStatus" :key="index">
                    <li><a class="link_style" href="#">{{item1}}</a></li>
                </ul>
            </div>
            <div v-else>Loading...</div>
        </div >
        
        <div class="fire-cause border-design">
            <p>Fire Cause</p>
            <hr>
            <div v-if="fireCause.length>0" >
                <ul v-for="item in fireCause" :key="item">
                    <li><a href="#">{{item}}</a></li>
                </ul>
            </div>
            <div v-else>Loading...</div>
        </div>
        
        <div class="geo-desc border-design">
            <div>
                <p>Geographic Description</p>
                <hr>
            </div>
            <div class="geo-desc-inner" >
                <div v-if="geoDesc.length>0" >
                    <ul v-for="item in geoDesc" :key="item">
                        <li><a href="#">{{item}}</a></li>
                    </ul>
                </div>
                <div v-else>Loading...</div>
            </div>
            
            
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: "Sidebar",
    data() {
        return {
            locations: [
                { lat: 48.4701, lng: -123.4667 },
                { lat: 48.4067, lng: -123.5143 },
                { lat: 49.2686, lng: -125.17 },
            ],
            fireCause:[],
            fireStatus:[],
            geoDesc:[],
        };
        
    },
    methods:{
        fetchAllFilters(){
        axios.get('http://127.0.0.1:8000/api/wildfire/')
            .then(response => {
            
            this.fireCause = response.data.fire_cause
            this.fireStatus = response.data.fire_status
            this.geoDesc = response.data.geographic_description
            })
            .catch(error => {
            console.error('Error fetching data:', error.message);
            });
        }
    
   
  },
  mounted(){
    console.log("Just Mounted")
    this.fetchAllFilters()
  }
};
</script>

<style>
.filter-container {
    width: 20vw;
    height: 100vh;
    background: #fff;
    display:grid;
    grid-template-rows: 0.5fr 2fr 2fr 5fr;
    margin:5px;
    
}
.title-container{
    margin-top: 2px;
    margin-left: 5px;
}
  
.geo-desc-inner{
    overflow: scroll;
    height: 220px;
}

.geo-desc{
    margin-bottom: 2px;
}

li{
    list-style-type: none;
}

p{
    font-size: 14px;
    font-weight: 500;
    margin-bottom: 10px;
}
 
a{
    font-size: 14px;
}
li{
    margin-bottom: 10px; 
}

.border-design{
    margin: 2px;
    box-shadow: 1px 1px 3px 0.4px rgba(0, 0, 0, 0.459);
    border-radius: 2px;
    padding: 10px;
    margin-bottom: 5px;
    margin-left: 5px;

}

hr{
    opacity: 0.2;
    margin-bottom: 2px;
}

 

</style>