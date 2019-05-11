<template>
  <div class="container">
    <h2>Unos podataka o prekršaju</h2>
    <form>
      <label for="form-row-vozac" class="form-row-title">Informacije o vozaču</label>
      <div class="form-row" id="form-row-vozac">
        <div class="form-group offset-md-2 col-md-4">
          <input type="text" class="form-control" id="imeVozaca" placeholder="Ime">
        </div>
        <div class="form-group col-md-4">
          <input type="text" class="form-control" id="prezimeVozaca" placeholder="Prezime">
        </div>
        <div class="form-group offset-md-2 col-md-8">
          <input type="text" class="form-control" id="jmbgVozaca" placeholder="JMBG">
        </div>
        <div class="form-group offset-md-2 col-md-8">
          <input type="text" class="form-control" id="brojDozvoleVozaca" placeholder="Broj vozačke dozvole">
        </div>
      </div>
      <hr>
      <label for="form-row-prekrsaj" class="form-row-title">Informacije o prekršaju</label>
      <div class="form-row" id="form-row-prekrsaj">
        <div class="form-group offset-md-2 col-md-8">
          <input type="text" class="form-control" id="ulicaPrekrsaja" placeholder="Ulica u kojoj je prekršaj načinjen">
        </div>
        <div class="form-group col-md-5 offset-md-2 form-inline">
          <label for="kolicinaAlkohola">Količina alkohola u krvi:&nbsp;</label>
          <input type="number" class="form-control" id="kolicinaAlkohola" value="0" min="0" step="0.05">
        </div>
        <div class="form-group col-md-3 form-inline">
          <label for="prisustvoPsihoaktivnihSups">Prisustvo psihoaktivnih supstanci:&nbsp;</label>
          <div class="form-check">
            <input class="form-check-input" type="checkbox" id="prisustvoPsihoaktivnihSups">
          </div>
        </div>
      </div>
      <button type="submit" class="btn btn-primary">Odredi prekršaj</button>
    </form>
  </div>
</template>

<script>
export default {
  name: "HelloWorld",
  props: {
    msg: String
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
hr {
  width: 75%;
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
</style>
