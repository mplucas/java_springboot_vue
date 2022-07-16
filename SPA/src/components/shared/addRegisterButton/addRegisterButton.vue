<template>
    <div>
        <button v-if="!clicked" type="button" class="btn btn-light" @click="startAddNewRegister()">Novo</button>
        <add-register-inline v-if="clicked" :register="{}" :columns="columns" @onSave="emitAdd($event)"
            @onCancel="cancelAddNewRegister()">
        </add-register-inline>
    </div>
</template>

<script>
import EditRegisterInLine from "../editRegisterInLine/EditRegisterInLine.vue"

export default {
    components: {
        "add-register-inline": EditRegisterInLine
    },
    data() {
        return {
            clicked: false
        }
    },
    props: {
        columns: {
            type: Array,
            required: true
        }
    },
    methods: {
        startAddNewRegister() {
            this.clicked = true
        },
        cancelAddNewRegister() {
            this.clicked = false
        },
        emitAdd(register) {
            this.$emit('onAdd', register)
            this.clicked = false
        }
    }
}
</script>

<style>
</style>