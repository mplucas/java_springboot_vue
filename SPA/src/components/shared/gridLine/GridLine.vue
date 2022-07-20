<template>
    <div>
        <ul v-if="!isUpdating" class="list-group list-group-horizontal list-group-flush">
            <li v-for="col in columns" class="list-group-item fixed-width">
                <span v-if="!col.type">{{ register[col.name] }}</span>
                <span v-if="col.type == 'dateTime'">{{ register[col.name] | date }}</span>
                <span v-if="col.type == 'dropDown'" class="drop-down-field">{{ register[col.name] }}</span>
            </li>
            <li v-if="canEdit" class="list-group-item fixed-width">
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
import EditRegisterInLine from "../editRegisterInLine/EditRegisterInLine.vue"
import date from "../../../filters/date.js"

export default {
    components: {
        "update-line": EditRegisterInLine
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
        },
        canEdit: {
            type: Boolean,
            default: true
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
    },
    filters: {
        date
    }
}
</script>

<style scoped>
.drop-down-field {
    font-weight: bold;
}
</style>