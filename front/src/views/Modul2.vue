<template>
  <div class="container">
    <form class="form-inline justify-content-center" v-on:submit="onSubmit">
        <div class="form-group mb-2">
          <input type="text" class="form-control" id="imeVozaca" placeholder="Tablice" v-model="forma.tablice"> 
        </div>
        <div class="form-group mx-sm-3 mb-2">
          <select class="form-control" id="zonaPrekrsaja" v-model="forma.tipPrekrsaja">
            <option value="Prolazak_kroz_crveno_svetlo">Prolazak kroz crveno svetlo</option>
            <option value="Prelazak_preko_pune_linije">Prelazak preko pune linije</option>
          </select>
        </div>
        <div class="form-group mx-sm-3 mb-2">
          <button type="submit" class="btn btn-primary">Evidentiraj prekršaj</button>
        </div>
    </form>

    <div class="container">
      <div class="row">
        <div class="col">
          <table class="table table-bordered">
            <tr>
              <th>Tablice</th><th>Vremena</th>
            </tr>
            <tr v-for="d in dates">
              <td>{{d.tablice}}</td>
              <td>
                <div v-for="p in d.prekrsaji">
                  {{ new Date(p) | formatDate }}
                </div>
              </td>
            </tr>
          </table>
        </div>
        <div class="col">
          <table class="table table-bordered">
            <tr>
              <th>Tablice</th><th>Vremena</th>
            </tr>
            <tr v-for="d in dates2">
              <td>{{d.tablice}}</td>
              <td>
                <div v-for="p in d.prekrsaji">
                  {{ new Date(p) | formatDate }}
                </div>
              </td>
            </tr>
          </table>
        </div>
        <div class="col">
          <table class="table table-bordered">
            <tr>
              <th>Tablice</th><th>Vremena</th>
            </tr>
            <tr v-for="d in dates3">
              <td>{{d.tablice}}</td>
              <td>
                <div v-for="p in d.prekrsaji">
                  {{ new Date(p) | formatDate }}
                </div>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    

    

   

    <!-- <div v-for="d in dates">
      {{ d.tablice }}
      <div v-for="p in d.prekrsaji">
        {{ new Date(p) | formatDate }}
      </div>
    </div>
    ---
    <div v-for="d in dates2">
      {{ d.tablice }}
      <div v-for="p in d.prekrsaji">
        {{ new Date(p) | formatDate }}
      </div>
    </div>
    ---
    <div v-for="d in dates3">
      {{ d.tablice }}
      <div v-for="p in d.prekrsaji">
        {{ new Date(p) | formatDate }}
      </div>
    </div> -->

  </div>
</template>

<script>
// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'
import axios from 'axios';


export default {
  name: 'modul2',
  components: {
    // HelloWorld
  }, methods : {
    onSubmit: function(e) {
      e.preventDefault();

      if (this.$data.forma.tablice != null && this.$data.forma.tablice != '') {
        axios.post('http://localhost:8080/nasilnicka', this.$data.forma)
        .then(response => {
          alert('Prekršaj uspešno evidentiran!')
          // this.$router.go()
          this.fun()
          
        })
        .catch(e => {
          alert('Evidentiranje prekršaja nije uspelo!')
        })
      } else {
        alert('Nisu popunjena sva polja!')
      }
    },
    fun: function() {
      axios.get('http://localhost:8080/nasilnicka/dates')
      .then(response => {
        this.$data.dates = response.data
      })

    axios.get('http://localhost:8080/nasilnicka/dates2')
      .then(response => {
        this.$data.dates2 = response.data
      })
    
    axios.get('http://localhost:8080/nasilnicka/dates3')
      .then(response => {
        this.$data.dates3 = response.data
      })
    }
  }, data: function() {
      return {
        forma: {
          tablice : null,
          tipPrekrsaja: 'Prolazak_kroz_crveno_svetlo'
        },
        dates: null,
        dates2: null,
        dates3: null
      }
  },
  mounted: function() {
    this.fun()
  }
}
</script>

<style scoped>
.container {
  margin-top: 1em; 
}
/* table, th, td {
  border: 1px solid black;
} */
</style>
