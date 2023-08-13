<template>
  <div class="parent-container">
    <div class="map-container">
      <GmapMap :center="{ lat: 48.4701, lng: -123.4667 }" :zoom="5" style="width: 70vw; height: 100%">
        <!-- Use v-for to iterate through an array of locations and create markers -->
        <GmapMarker v-for="(location, index) in locations" :key="index" :position="location" />
      </GmapMap>
    </div>
    <div class="filter-container"></div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      locations: [
        { lat: 48.4701, lng: -123.4667 },
        { lat: 48.4067, lng: -123.5143 },
        { lat: 49.2686, lng: -125.17 },
        // Add more locations as needed
      ],
    };
  },
};
</script>

<style>
.parent-container {
  display: flex;
}
.map-container {
  background: blue;
}
.filter-container {
  width: 30vw;
  height: 100vh;
  background: #fff;
}
.parent-container {
  background: #fff;
}
</style>
