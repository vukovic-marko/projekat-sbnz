<template>
  <div class="home">
    <CodeEditor/>
  </div>
</template>

<script>
// @ is an alias to /src
import CodeEditor from '@/components/CodeEditor.vue'

export default {
  name: 'about',
  components: {
    CodeEditor
  }
}
</script>
