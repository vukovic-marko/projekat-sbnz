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

    <p class="lead">
      Spiskovi vozila koje je potrebno isključiti iz saobraćaja radi izdavanja njihovim vozačima kazne za nasilničku vožnju.
    </p>

    <div class="container">
      <div class="row">
        <div class="col">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th colspan="3">Vozila koja su dva ili više puta u roku od 10 minuta prošla svetlosni saobraćajni znak kada mu je tim znakom zabranjen prolaz</th>
              </tr>
              <tr>
                <th>Tablice</th><th colspan="2">Vremena</th>
              </tr>
            </thead>
            <tr v-for="(d,idx) in dates" :key="d">
              <td>{{d.tablice}}</td>
              <td>
                <div v-for="p in d.prekrsaji" :key="p">
                  {{ new Date(p) | formatDate }}
                </div>
              </td>
              <td>
                <button type="button" v-on:click="remove(idx)" class="close" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </td>
            </tr>
          </table>
        </div>

        <div class="col">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th colspan="3">Vozila koja su tri ili više puta u roku od jednog sata prošla svetlosni saobraćajni znak kada mu je tim znakom zabranjen prolaz</th>
              </tr>
              <tr>
                <th>Tablice</th><th colspan="2">Vremena</th>
              </tr>
            </thead>
            <tr v-for="(d,idx) in dates2" :key="d">
              <td>{{d.tablice}}</td>
              <td>
                <div v-for="p in d.prekrsaji" :key="p">
                  {{ new Date(p) | formatDate }}
                </div>
              </td>
              <td>
                <button type="button" v-on:click="remove2(idx)" class="close" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </td>
            </tr>
          </table>
        </div>

        <div class="col">
          <table class="table table-bordered">
            <thead>
              <tr>
                <th colspan="3">Vozila koja su se kretala preko neisprekidane uzdužne linije koja razdvaja kolovozne trake po smerovima kretanja</th>
              </tr>
              <tr>
                <th>Tablice</th><th colspan="2">Vremena</th>
              </tr>
            </thead>
            <tr v-for="(d,idx) in dates3" :key="d">
              <td>{{d.tablice}}</td>
              <td>
                <div v-for="p in d.prekrsaji" :key="p">
                  {{ new Date(p) | formatDate }}
                </div>
              </td>
              <td>
                <button type="button" v-on:click="remove3(idx)" class="close" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
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
        // eslint-disable-next-line
        .then(response => {
          alert('Prekršaj uspešno evidentiran!')
          // this.$router.go()
          this.fun()
          
        })
        // eslint-disable-next-line
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
    },
    remove: function(idx) {
      axios.delete('http://localhost:8080/nasilnicka/dates/'+idx)
      // eslint-disable-next-line
      .then(response => {
        this.fun()
      })
    },
    remove2: function(idx) {
       axios.delete('http://localhost:8080/nasilnicka/dates2/'+idx)
      // eslint-disable-next-line
      .then(response => {
        this.fun()
      })
    },
    remove3: function(idx) {
       axios.delete('http://localhost:8080/nasilnicka/dates3/'+idx)
      // eslint-disable-next-line
      .then(response => {
        this.fun()
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
.lead {
  margin-top: 1em;
  margin-bottom: 2em;
}
</style>
