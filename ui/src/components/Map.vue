<template>
  <div class="parent-container">
    <div class="map-container">
      <p class="map-title">BC 2023 Wildfire Viewer</p>
      <GmapMap
        :center="{ lat: centerLat, lng: centerLng }"
        :zoom="8"
        style="width: 80vw; height: 100%; margin-top: -10px"
      >
        <GmapMarker
          @click="handleMarkerClick(index)"
          v-for="(location, index) in retrievedLocations"
          :key="index"
          :position="location"
        >
        </GmapMarker>
      </GmapMap>
      <div v-if="filterName != ''">
        <div class="floating-div border-design">
          <div class="filter__title">{{ filterQuery }}:</div>
          <hr />
          {{ filterName }}
        </div>
      </div>

      <div v-if="isSelected">
        <DisplayFireInfo
          class="display-fire-info"
          :capture="capturedInfo"
          @close="closeModal"
        />
      </div>
      <div v-else></div>

      <div @click="reDownload" class="btn--reload floating-div-reload">
        Failed? Reload.
      </div>
    </div>

    <Sidebar
      :activateRedownload="activateRedownload"
      :feature_list="feature_list"
      @filterData="triggerFilterSelected"
    />
  </div>
</template>

<script>
import Sidebar from "./Sidebar.vue";
import DisplayFireInfo from "./DisplayFireInfo.vue";
import axios from "axios";

export default {
  components: {
    Sidebar,
    DisplayFireInfo,
  },
  data() {
    return {
      retrievedLocations: [],
      filterName: "",
      filterQuery: "",
      feature_list: null,
      capturedInfo: {},
      isSelected: false,
      errorMessage: "",
      activateRedownload: false,
      centerLat: 53.283333,
      centerLng: -123.133333,
    };
  },
  methods: {
    fetchAllData() {
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
          console.log(error.message);
        });
    },
    triggerFilterSelected(link, fName, fQuery) {
      this.retrievedLocations = [];
      this.filterName = fName;
      this.filterQuery = fQuery
        .replace(/_/g, " ")
        .toLowerCase()
        .split(" ")
        .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
        .join(" ");
      axios
        .get(link)
        .then((response) => {
          this.feature_list = response.data;
          this.feature_list.forEach((element) => {
            this.retrievedLocations.push({
              lat: element.properties.LATITUDE,
              lng: element.properties.LONGITUDE,
            });
          });
          this.calculateAverage();
          // if (this.retrievedLocations.length === 1) {
          //   this.centerLat = this.retrievedLocations[0].lat;
          //   this.centerLng = this.retrievedLocations[0].lng;
          //   console.log("The values are: ");
          //   console.log(this.retrievedLocations[0].lat);
          //   console.log(this.retrievedLocations[0].lng);
          // }
        })
        .catch((error) => {
          console.error(error.message);
        });
    },
    handleMarkerClick(index) {
      this.capturedInfo = this.feature_list[index].properties;
      this.isSelected = true;
    },
    closeModal() {
      this.isSelected = false;
    },
    reDownload() {
      this.fetchAllData();
      this.activateRedownload = true;
    },

    calculateAverage() {
      let total = this.retrievedLocations.length;
      let totalLat = 0;
      let totalLng = 0;
      this.retrievedLocations.forEach((element) => {
        totalLat = totalLat + element.lat;
        totalLng = totalLng + element.lng;
      });
      this.centerLat = totalLat / total;
      this.centerLng = totalLng / total;
      console.log(this.centerLat);
      console.log(this.centerLng);
    },
  },
  mounted() {
    this.fetchAllData();
  },
};
</script>

<style>
.parent-container {
  display: flex;
}

.parent-container {
  background: #fff;
}

.parent-container {
  position: relative;
}

.filter__title {
  color: #000000;
  font-weight: bold;
}

hr {
  opacity: 0.2;
  margin-bottom: 5px;
}

.floating-div-reload {
  height: 40px;
  width: fit-content;
  position: absolute;
  background: #fff;
  border-radius: 5px;
  top: 0;
  left: -200px;
  margin-top: 70px;
  transform: translateX(700px);
  z-index: 1;
}

.btn--reload {
  height: 30px;
  background: #000;
  width: 150px;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  cursor: pointer;
  transition: all 0.3s;
  font-size: 13px;
  opacity: 0.7;
}

.btn--reload:hover {
  background: #fff;
  color: #000;
  border-style: solid;
  border-width: 1px;
  border-color: #000;
}

.btn--reload:active {
  background: #1f1f1f;
  color: #cecece;
  border-style: solid;
  border-width: 1px;
  border-color: #cecece;
}
.map-container {
  overflow: hidden;
  height: 97vh;
  width: 80vw;
  margin-top: 10px;
  margin-left: 10px;
  box-shadow: 1px 1px 3px 0.4px rgba(38, 38, 38, 0.459);
  border-radius: 5px;
}
.map-title {
  width: 100%;
  background: #1f1f1f;
  color: #cecece;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 25px;
}
</style>
