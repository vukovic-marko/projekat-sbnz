<template>
  <div class="about">
    <h1>Izmena pravila</h1>
    <div class="cm">
        <codemirror v-model="code" :options="cmOptions"></codemirror>
        <div class="btns">
            <button v-on:click="save" class="btn btn-primary">Sacuvaj izmene</button>
            <button v-on:click="update" class="btn btn-primary">Instaliraj pravila</button>
            <button v-on:click="refresh" class="btn btn-primary">Osveži projekat</button>
        </div>
    </div>
  </div>
</template>

<script>
// require component
import { codemirror } from 'vue-codemirror'
import axios from 'axios';


// require styles
import 'codemirror/lib/codemirror.css'

export default {
  data() {
    return {
      code: '',
      cmOptions: {
        // codemirror options
        tabSize: 4,
        mode: 'text/plain',
        theme: 'base16-dark',
        lineNumbers: true,
        line: true
      }
    }
  },
  components: {
    codemirror
  },
  methods: {
      save: function(e) {
          e.preventDefault();
          
          
            var textToWrite = this.$data.code;
            var textFileAsBlob = new Blob([textToWrite], {
                type: "text/plain;charset=utf-8"
            });

            var fileNameToSaveAs = "pravilo.drl";

            var downloadLink = document.createElement("a");
            downloadLink.download = fileNameToSaveAs;
            downloadLink.innerHTML = "Download File";
            if (window.webkitURL != null) {
                // Chrome allows the link to be clicked
                // without actually adding it to the DOM.
                downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
            } else {
                // Firefox requires the link to be added to the DOM
                // before it can be clicked.
                downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
                // downloadLink.onclick = destroyClickedElement;
                downloadLink.style.display = "none";
                document.body.appendChild(downloadLink);
            }

            downloadLink.click();
      },
      update: function(e) {
        e.preventDefault();

        axios.get('http://localhost:8080/maven')
        // eslint-disable-next-line
        .then(response => {
          alert('Pravila uspesno instalirana!');
        })
        // eslint-disable-next-line
        .catch(e => {
          alert('Neuspesno instaliranje pravila!');
        })
        
      },
      refresh: function(e) {
        e.preventDefault();

        axios.get('http://localhost:8080/refresh')
        // eslint-disable-next-line
        .then(response => {
          alert('Projekat uspesno osvezen!');
        })
        // eslint-disable-next-line
        .catch(e => {
          alert('Neuspesno osvezavanje projekta!');
        })
      }
  }
}
</script>

<style scoped>
.cm  { 
  text-align:left;
  height: 100px;
}
.about {
  height: 100%;
}
button {
    margin-left: 5px;
    margin-right: 5px;
}
.btns {
    text-align: center;
}
</style>
