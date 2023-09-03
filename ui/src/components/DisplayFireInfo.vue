<template>
  <div class="primary-height floating-div-display-info border-design">
    <div class="display--title">
      <p class="fire--bc--title">
        Latitude: {{ capture.LATITUDE }} | Longitude: {{ capture.LONGITUDE }}
      </p>
      <span @click="closePop" class="material-symbols-outlined"> close </span>
    </div>
    <hr />

    <span class="fire--bc--list">Fire Number: </span
    ><span class="fire-info-value"> {{ capture.FIRE_NUMBER }}</span
    ><br />
    <span class="fire--bc--list">Response Type Description: </span
    ><span class="fire-info-value"> {{ capture.RESPONSE_TYPE_DESC }}</span
    ><br />
    <span class="fire--bc--list">Ignition Date: </span
    ><span class="fire-info-value"> {{ capture.IGNITION_DATE }}</span
    ><br />
    <span class="fire--bc--list">Fire Out Date: </span
    ><span class="fire-info-value"> {{ capture.FIRE_OUT_DATE }}</span
    ><br />
    <span class="fire--bc--list">Geographic Description: </span
    ><span class="fire-info-value"> {{ capture.GEOGRAPHIC_DESCRIPTION }}</span
    ><br />
    <span class="fire--bc--list">Fire Status: </span
    ><span class="fire-info-value"> {{ capture.FIRE_STATUS }}</span
    ><br />
    <span class="fire--bc--list">Fire Cause: </span
    ><span class="fire-info-value"> {{ capture.FIRE_CAUSE }}</span
    ><br />
    <span class="fire--bc--list">Fire URL: </span
    ><span class="fire-info-value">
      <a target="_blank" :href="capture.FIRE_URL">Check Details</a></span
    ><br />
    <br />
    <hr />
    <p class="info-text">
      Zoom In and Out as required to <br />properly view Wildfire per location
    </p>
  </div>
</template>

<script>
export default {
  props: {
    capture: Object,
  },
  data() {
    return {
      postData: {},
    };
  },
  methods: {
    closePop() {
      this.$emit("close");
    },
    downloadCSV() {
      axios
        .get("http://165.232.147.254:8082/api/wildfire/search/")
        .then((response) => {
          this.feature_list = response.data.features;
          this.feature_list.forEach((element) => {
            this.retrievedLocations.push({
              lat: element.properties.LATITUDE,
              lng: element.properties.LONGITUDE,
            });
          });
        })
        .catch((error) => {
          console.error(error.message);
        });
    },
  },
};
</script>

<style>
.display--title {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 15px;
}
.material-symbols-outlined {
  cursor: pointer;
}
.primary-height {
  height: fit-content;
}
.fire-info-value {
  font-weight: 100;
  font-size: 12px;
}
.fire--bc--title {
  font-size: 12px;
  text-align: center;
}
.fire--bc--list {
  font-size: 12px;
}

.btn--download {
  height: 30px;
  background: red;
  width: 100%;
  padding: 5px 2px;
  margin-left: 5px;
  margin-right: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  cursor: pointer;
  transition: all 0.3s;
  font-size: 13px;
}

.btn--download:hover {
  background: #fff;
  color: #000;
  border-style: solid;
  border-width: 1px;
  border-color: #000;
}

.btn--download:active {
  background: #1f1f1f;
  color: #cecece;
  border-style: solid;
  border-width: 1px;
  border-color: #cecece;
}

.info-text {
  font-weight: 300;
  color: #000;
}
</style>
