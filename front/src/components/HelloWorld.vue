<template>
  <div class="container">
    <h2>Unos podataka o prekršaju</h2>

    <form v-on:submit="onSubmit">
      <label for="form-row-vozac" class="big-title">Podaci o vozaču</label>
      <div class="form-row form-row-vozac">
        <div class="form-group col-md-6">
          <input type="text" class="form-control" id="imeVozaca" placeholder="Ime" v-model.trim="zapisnik.vozac.ime"> 
        </div>
        <div class="form-group col-md-6">
          <input type="text" class="form-control" id="prezimeVozaca" placeholder="Prezime" v-model.trim="zapisnik.vozac.prezime">
        </div>
      </div>
      <div class="form-group">
        <input type="text" class="form-control" id="jmbgVozaca" placeholder="JMBG" v-model.trim="zapisnik.vozac.jmbg">
      </div>
          
      <label for="form-row-dozvola" class="small-title">Podaci o vozačkoj dozvoli</label>
      <div class="form-row form-row-dozvola">
        <div class="form-group col-md-6">
          <input type="text" class="form-control" id="brojDozvoleVozaca" placeholder="Broj vozačke dozvole" v-model.trim="zapisnik.vozac.brojDozvole">
        </div>
        <div class="form-group col-md-6">
          <!-- <input type="text" class="form-control" id="tipDozvoleVozaca" placeholder="Tip vozačke dozvole"> -->
          <select class="form-control" id="tipDozvoleVozaca" v-model="zapisnik.vozac.tipDozvole">
            <option value="TRAJNA">Trajna</option>
            <option value="PROBNA">Probna</option>
          </select>
        </div>
      </div>
      <hr>

      <label for="form-row-vozac" class="big-title">Podaci o prekršaju</label>
      <div class="form-row form-row-vozac">
        <div class="form-group col-md-6">
          <input type="text" class="form-control" id="ulicaPrekrsaja" placeholder="Ulica u kojoj je prekršaj počinjen" v-model.trim="zapisnik.ulica">
        </div>
        <div class="form-group col-md-3">
          <select class="form-control" id="naseljePrekrsaja" v-model="nasMest">
            <option>U naselju</option>
            <option>Van naselja</option>
          </select>
        </div>
        <div class="form-group col-md-3">
          <select class="form-control" id="zonaPrekrsaja" v-model="zapisnik.zona">
            <option value="REDOVNA">Redovna</option>
            <option value="ZONA_USPORENOG_SAOBRACAJA">Usporena</option>
            <option value="ZONA_30">Zona 30</option>
            <option value="ZONA_SKOLE">Zona škole</option>
          </select>
        </div>
      </div>
      
      <label for="form-row-voznja" class="small-title">Podaci o brzini kretanja</label>
      <div class="form-row form-row-voznja">
        <div class="form-group col-md-6">
          <input type="number" id="brzinaKretanjaPrekrsaja" class="form-control" min="1" placeholder="Ostvarena brzina kretanja" v-model.number="zapisnik.ostvarenaBrzina">
        </div>
        <div class="form-group col-md-6">
          <input type="number" id="dozvoljenaBrzinaPrekrsaja" class="form-control" min="1" placeholder="Dozvoljena brzina kretanja" v-model.number="zapisnik.dozvoljenaBrzina">
        </div>
      </div>
          
      <label for="form-row-alkohol" class="small-title">Podaci o alkoholisanosti i psihoaktivnim supstancama</label>
      <div class="form-row form-row-alkohol justify-content-md-center">
        <div class="form-group col-md-6">
          <input type="number" id="kolicinaAlkoholaPrekrsaja" class="form-control" min="0" step="0.01" placeholder="Količina alkohola u krvi" v-model.number="zapisnik.prisustvoAlkohola">
        </div>
        <!-- <div class="form-group col-md-5">
          <label for="prisustvoPsihoaktivnihPrekrsaja" class="form-check-label col-md-5">Psihoaktivne supstance: </label>
        </div> -->
        <div class="form-group col-md-6">
          <input type="checkbox" class="form-check-input" id="prisustvoPsihoaktivnihPrekrsaja" v-model="zapisnik.prisustvoPsihoaktivnihSupstanci">
          <label for="prisustvoPsihoaktivnihPrekrsaja" class="form-check-label">&nbsp;prisustvo prihoaktivnih susptanci</label>
        </div>
        <div class="form-group col-md-8">
          <input type="checkbox" class="form-check-input" id="saobracajnaNesrecaPrekrsaj" v-model="zapisnik.saobracajnaNesreca">
          <label for="saobracajnaNesrecaPrekrsaj" class="form-check-label">&nbsp;izazvana saobraćajna nesreća</label>
        </div>
        <div class="form-group col-md-8">
          <input type="checkbox" class="form-check-input" id="ometaPrekrsaj" v-model="zapisnik.ometa">
          <label for="ometaPrekrsaj" class="form-check-label">&nbsp;ometa službeno lice</label>
        </div>
        <div class="form-group col-md-8">
          <input type="checkbox" class="form-check-input" id="detePrekrsaj" v-model="zapisnik.prisutnoDete">
          <label for="detePrekrsaj" class="form-check-label">&nbsp;prisutno dete mlađe od 12 godina</label>
        </div>
        <!-- </div> -->
      </div>
      <button type="submit" class="btn btn-primary">Evidentiraj prekršaj</button>
    </form>

    <modal name="hello-world" height="auto" :scrollable="true">
      <div class="modaldiv">
        <h1>Prekršaj uspešno evidentiran!</h1>
        <h2 v-if="resp.zapisnik.prekoracenjeBrzine != null">Prekršaj prekoračenja brzine:</h2>
        <p v-if="resp.zapisnik.prekoracenjeBrzine != null">
          Zatvorska kazna: {{resp.zapisnik.prekoracenjeBrzine.zatvorskaKazna}}<br>
          Novčana kazna: {{resp.zapisnik.prekoracenjeBrzine.novcanaKazna}}<br>
          Kazneni poeni: {{resp.zapisnik.prekoracenjeBrzine.kazneniPoeni}}<br>
          Zabrana upravljanja: {{resp.zapisnik.prekoracenjeBrzine.zabranaUpravljanja}}<br>
        </p>
        <h2 v-if="resp.zapisnik.voznjaPodUticajem != null">Prekršaj vožnje pod uticajem:</h2>
        <p v-if="resp.zapisnik.voznjaPodUticajem != null">
          Zatvorska kazna: {{resp.zapisnik.voznjaPodUticajem.zatvorskaKazna}}<br>
          Novčana kazna: {{resp.zapisnik.voznjaPodUticajem.novcanaKazna}}<br>
          Kazneni poeni: {{resp.zapisnik.voznjaPodUticajem.kazneniPoeni}}<br>
          Zabrana upravljanja: {{resp.zapisnik.voznjaPodUticajem.zabranaUpravljanja}}<br>
        </p>
        <p v-if="resp.oduzimanjeDozvole == true">
          Vozacu je potrebno oduzeti dozvolu zato što je u prethodna 24 meseca sakupio <b>{{resp.godisnjiKazneniPoeni}}</b> kaznenih poena.
        </p>
      </div>
    </modal>
  </div>
</template>

<script>

import axios from 'axios';
// import { constants } from 'crypto';

export default {
  name: "HelloWorld",
  props: {
    msg: String
  },
  data: function() {
    return {
        zapisnik: {
          vozac: {
            ime: '',
            prezime: '',
            jmbg: '',
            brojDozvole: '',
            tipDozvole: 'TRAJNA'
          },
          naseljenoMesto: null,
          zona: "REDOVNA",
          dozvoljenaBrzina: '',
          ostvarenaBrzina: '',
          ulica: '',
          prisustvoAlkohola: '',
          prisustvoPsihoaktivnihSupstanci: false,
          ometa: false,
          saobracajnaNesreca: false,
          prisutnoDete: false
        },
        nasMest: "U naselju",
        resp: {
          zapisnik: {
            prekoracenjeBrzine: {
              zatvorskaKazna: null,
              novcanaKazna: null,
              kazneniPoeni: null,
              zabranaUpravljanja: null
            },
            voznjaPodUticajem: {
              zatvorskaKazna: null,
              novcanaKazna: null,
              kazneniPoeni: null,
              zabranaUpravljanja: null
            }
          },
          godisnjiKazneniPoeni: null,
          oduzimanjeDozvole: null
        }
      }
  },
  methods: {
    onSubmit: function(e) {
      e.preventDefault();
      if (this.$data.zapisnik.vozac.ime != null && this.$data.zapisnik.vozac.ime != '' &&
          this.$data.zapisnik.vozac.prezime != null && this.$data.zapisnik.vozac.prezime != '' &&
          this.$data.zapisnik.vozac.jmbg != null && this.$data.zapisnik.vozac.jmbg != '' &&
          this.$data.zapisnik.vozac.brojDozvole != null && this.$data.zapisnik.vozac.brojDozvole != '' &&
          this.$data.zapisnik.ulica != null && this.$data.zapisnik.ulica != '' &&
          this.$data.zapisnik.ostvarenaBrzina != null && this.$data.zapisnik.ostvarenaBrzina != '' &&
          this.$data.zapisnik.dozvoljenaBrzina != null && this.$data.zapisnik.dozvoljenaBrzina != '' &&
          this.$data.zapisnik.prisustvoAlkohola != null && this.$data.zapisnik.prisustvoAlkohola != null) {
        // console.log(this.$data.nasMest)
        
        if (this.$data.nasMest == "U naselju") {
          // console.log("yes")
          this.$data.zapisnik.naseljenoMesto = true
        } else if (this.$data.nasMest == "Van naselja") {
          // console.log("no")
          this.$data.zapisnik.naseljenoMesto = false
        }

        // console.log(this.$data.zapisnik);

        // console.log('saljem zahtev')
        axios.post('http://localhost:8080/zapisnik', this.$data.zapisnik)
        .then(response => {
          // console.log('uspesno')
          // console.log(response.data)
          // console.log(response.data.zapisnik.voznjaPodUticajem)
          // console.log(response.data.zapisnik.prekoracenjeBrzine)
          this.$data.resp = response.data
          this.$modal.show('hello-world');

        })
        // eslint-disable-next-line
        .catch(e => {
          alert('Slanje zapisnika nije uspelo!')
          // console.log(e);
        })
      } else {
        alert('Potrebno je popuniti sva polja!')
      }
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
hr {
  width: 90%;
}
.form-row-title {
  margin: 10px 0 10px 0;
}
h2 {
  margin: 20px 0 10px 0;
}
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
form {
  width: 85%;
  padding-left: 15%;
}
label.big-title {
  font-size: 16pt;
}
label.small-title {
  font-size: 12pt;
  font-weight: lighter;
}
label {
  white-space: nowrap;
}
.modaldiv {
  padding: 5px;
}
</style>
