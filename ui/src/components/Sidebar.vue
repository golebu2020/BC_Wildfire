<template>
    <div class="filter-container">
        <div class="fire-status border-design">
            
            <p class="align-text-center">Fire Status</p>
            <hr>
            <div v-if="fireStatus.length>0" >
                <ul v-for="(item, index) in fireStatus" :key="index">
                    <li><a @click="handleFilterClicked('fire_status',item)" class="link_style" href="#">{{item}}</a></li>
                </ul>
            </div>
            <div class="loading-class" v-else>Loading...</div>
        </div >
        
        <div class="fire-cause border-design">
            <p class="align-text-center">Fire Cause</p>
            <hr>
            <div v-if="fireCause.length>0" >
                <ul v-for="item in fireCause" :key="item">
                    <li><a @click="handleFilterClicked('fire_cause',item)" href="#" class="link_style" >{{item}}</a></li>
                </ul>
            </div>
            <div class="loading-class" v-else>Loading...</div>
        </div>
        
        <div class="geo-desc border-design">
            <div>
                <p class="align-text-center">Geographic Description</p>
                <hr>
            </div>
            <div class="geo-desc-inner" >
                <div v-if="geoDesc.length>0" >
                    <ul v-for="item in geoDesc" :key="item">
                        <li><a @click="handleFilterClicked('geographic_description',item)" href="#" class="link_style" >{{item}}</a></li>
                    </ul>
                </div>
                <div class="loading-class" v-else>Loading...</div>
            </div>
            
        </div>
        <div @click="downloadCSV" class="btn--download">Download data</div>
    </div>
</template>

<script>
import axios from 'axios';
import { jsonToPlainText } from "json-to-plain-text";
export default {
    name: "Sidebar",
    props:{
        feature_list:Array,
    },
    data() {
        return {
            fireCause:[],
            fireStatus:[],
            geoDesc:[],
            permLink:"",
            jsonData: {},
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
                console.error(error.message);
                });
        },

        handleFilterClicked(query,value){
            this.permLink = `http://127.0.0.1:8000/api/wildfire/search/?${query}=${value}`
            this.$emit('filterData', this.permLink, value, query);
        },
        downloadCSV(){
            this.jsonData = this.feature_list
            const plainText = jsonToPlainText(this.jsonData);
            console.log(plainText);

            const blob = new Blob([plainText], { type: 'text/plain' }); 
            const url = URL.createObjectURL(blob); 

            const a = document.createElement('a');
            a.href = url;
            a.download = 'BC_Wildfire.txt'; 
            a.click();

            URL.revokeObjectURL(url);
        }

  },
  mounted(){
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
    grid-template-rows: 0.5fr 2fr 2fr 5fr 1fr;
    margin:5px;
    
}
.title-container{
    margin-top: 2px;
    margin-left: 5px;
}
  
.geo-desc-inner{
    overflow: scroll;
    height: 200px;
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
    text-decoration: none;
    color: rgb(103, 103, 103);
    transition: all 0.3s;
}

a:hover{
    color:crimson;
    font-weight: bold;
}
li{
    margin-bottom: 5px; 
   
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

.loading-class{
    text-align: center;
    margin-top: 20px;
    font-size: 14px;
    color:rgb(145, 145, 145);
}
.align-text-center{
    text-align: start;
}
.fire--bc--title{
    font-size: 15px;
    text-align: center;
}

 

</style>