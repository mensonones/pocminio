<template>
  <v-content>
    <div class="container">
      <div class="large-12 medium-12 small-12 cell">
        <label>
          File
          <input type="file" id="file" ref="file" v-on:change="handleFileUpload()" />
        </label>
      </div>
    </div>
    <v-col class="text-center" cols="6" sm="4">
      <div class="my-2">
        <v-btn small color="indigo" v-on:click="submitFile()">Enviar</v-btn>
      </div>
    </v-col>

    <v-container class="grey lighten-5">
      <v-row no-gutters>
        <v-col>
          <v-card>Nome do Arquivo</v-card>
          <v-card
            class="mx-auto"
            outlined
            tile
            v-for="(item, index) in objectsBucket"
            :key="index"
          >{{ item.objectName }}</v-card>
        </v-col>
        <v-col order="12">
          <v-card class="pa-2" outlined tile>Ação</v-card>
          <v-card class="pa-2" outlined tile v-for="(item, index) in objectsBucket" :key="index">
            <a href="#" v-on:click="getFile(item.objectName)">Download</a>
          </v-card>
        </v-col>
        <!-- <v-col order="1">
          <v-card class="pa-2" outlined tile>Third, but first</v-card>
        </v-col>-->
      </v-row>
    </v-container>
  </v-content>
</template>

<script>
import HelloWorld from "./components/HelloWorld";
import axios from "axios";

export default {
  name: "App",
  components: {},
  data: () => ({
    nameBucket: "",
    file: "",
    objectsBucket: []
  }),
  methods: {
    handleFileUpload() {
      this.file = this.$refs.file.files[0];
    },
    submitFile() {
      let formData = new FormData();

      formData.append("file", this.file);

      axios
        .post(
          "http://localhost:8080/api/v1/minio/object/upload?bucket=teste",
          formData,
          {
            headers: {
              "Content-Type": "multipart/form-data"
            }
          }
        )
        .then(function() {
          console.log("SUCCESS!!");
        })
        .catch(function() {
          console.log("FAILURE!!");
        });
    },
    getFile(nomeFile) {
      window.open(
        `http://localhost:8080/api/v1/minio/object/downloadFile?bucket=teste&object=${nomeFile}`
      );
    }
  },
  mounted() {
    axios
      .get("http://localhost:8080/api/v1/minio/object?bucket=teste")
      .then(response => {
        console.log(response.data);
        this.objectsBucket = response.data.data.result;
      })
      .catch(error => {
        console.log(error);
      });
  }
};
</script>
