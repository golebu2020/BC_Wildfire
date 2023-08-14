<template>
  <div class="primary-height floating-div-display-info border-design ">
    <div class="display--title">
        <h4>Latitude: {{capture.LATITUDE}} | Longitude: {{capture.LONGITUDE}}</h4>
        <span class="material-symbols-outlined">
            close
        </span>
    </div>
    <hr>
    <div v-if="!capture.isObjectEmpty">
        <span>Fire Number: </span><span class="fire-info-value"> {{capture.FIRE_NUMBER}}</span><br>
        <span>Response Type Description: </span><span class="fire-info-value"> {{capture.RESPONSE_TYPE_DESC}}</span><br>
        <span>Ignition Date: </span><span class="fire-info-value"> {{capture.IGNITION_DATE}}</span><br>
        <span>Fire Out Date: </span><span class="fire-info-value"> {{capture.FIRE_OUT_DATE}}</span><br>
        <span>Geographic Description: </span><span class="fire-info-value"> {{capture.GEOGRAPHIC_DESCRIPTION}}</span><br>
        <span>Fire Status: </span><span class="fire-info-value"> {{capture.FIRE_STATUS}}</span><br>
        <span>Fire Cause: </span><span class="fire-info-value"> {{capture.FIRE_CAUSE}}</span><br>
        <span>Fire URL: </span><span class="fire-info-value"> <a  target="_blank"  :href="capture.FIRE_URL">Check Details</a></span><br>
        <br>
    </div>
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
    
}
</script>

<style>
.display--title{
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-left: 10px;
}
.material-symbols-outlined{
    cursor: pointer;
}
.primary-height{
    height: fit-content;
}
.fire-info-value{
    font-weight: 100;
    font-size: 14px;
}

</style>