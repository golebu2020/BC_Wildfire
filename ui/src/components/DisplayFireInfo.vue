<template>
  <div class="primary-height floating-div-display-info border-design ">
    <div class="display--title">
        <p class="fire--bc--title">Latitude: {{capture.LATITUDE}} | Longitude: {{capture.LONGITUDE}}</p>
        <span @click="closePop" class="material-symbols-outlined">
            close
        </span>
    </div>
    <hr>
    
    <span class="fire--bc--list">Fire Number: </span><span class="fire-info-value"> {{capture.FIRE_NUMBER}}</span><br>
    <span class="fire--bc--list">Response Type Description: </span><span class="fire-info-value"> {{capture.RESPONSE_TYPE_DESC}}</span><br>
    <span class="fire--bc--list">Ignition Date: </span><span class="fire-info-value"> {{capture.IGNITION_DATE}}</span><br>
    <span class="fire--bc--list">Fire Out Date: </span><span class="fire-info-value"> {{capture.FIRE_OUT_DATE}}</span><br>
    <span class="fire--bc--list">Geographic Description: </span><span class="fire-info-value"> {{capture.GEOGRAPHIC_DESCRIPTION}}</span><br>
    <span class="fire--bc--list">Fire Status: </span><span class="fire-info-value"> {{capture.FIRE_STATUS}}</span><br>
    <span class="fire--bc--list">Fire Cause: </span><span class="fire-info-value"> {{capture.FIRE_CAUSE}}</span><br>
    <span class="fire--bc--list">Fire URL: </span><span class="fire-info-value"> <a  target="_blank"  :href="capture.FIRE_URL">Check Details</a></span><br>
    <br>
    
  </div>
</template>

<script>
export default {
    props:{
        capture:Object
    },
    data(){
        return {
            fireNo:{},
        }
    },
    methods: {
        closePop(){
            this.$emit('close')
        }
    },
    
}
</script>

<style>
.display--title{
    display: flex;
    align-items: center;
    justify-content: space-between;
    gap: 15px;
}
.material-symbols-outlined{
    cursor: pointer;
}
.primary-height{
    height: fit-content;
}
.fire-info-value{
    font-weight: 100;
    font-size: 12px;
}
.fire--bc--title{
    font-size: 12px;
    text-align: center;
}
.fire--bc--list{
    font-size: 12px;
}

</style>