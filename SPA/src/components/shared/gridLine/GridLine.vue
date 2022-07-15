<template>
    <div>
        <ul v-if="!isUpdating" class="list-group list-group-horizontal list-group-flush">
            <li v-for="col in columns" class="list-group-item fixed-width">
                {{ register[col.name] }}
            </li>
            <li class="list-group-item fixed-width">
                <button type="button" class="btn btn-light" @click="startUpdatingProduct()">Alterar</button>
                <button type="button" class="btn btn-danger" @click="emitDelete(register)">Excluir</button>
            </li>
        </ul>
        <update-line v-if="isUpdating" :register="register" :columns="columns" @onSave="emitUpdate($event)"
            @onCancel="cancelUpdatingProduct()">
        </update-line>
    </div>
</template>

<script>
import SaveRegisterInLine from "../saveRegisterInLine/SaveRegisterInLine.vue"

export default {
    components: {
        "update-line": SaveRegisterInLine
    },
    data() {
        return {
            isUpdating: false
        }
    },
    props: {
        register: {
            type: Object,
            required: true
        },
        columns: {
            type: Array,
            required: true
        }
    },
    methods: {
        startUpdatingProduct() {
            this.isUpdating = true
        },
        cancelUpdatingProduct() {
            this.isUpdating = false
        },
        emitUpdate(register) {
            this.$emit('onUpdate', register)
            this.isUpdating = false
        },
        emitDelete(register) {
            this.$emit('onDelete', register)
        }
    }
}
</script>

<style>
</style>