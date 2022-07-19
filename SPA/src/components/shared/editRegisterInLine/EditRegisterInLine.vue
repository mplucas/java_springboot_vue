<template>
    <div>
        <div class="input-group input-group-sm mb-3">
            <div v-for="col in columns" class="fixed-width">
                <input v-if="!col.type" :placeholder="col.display" v-model="register[col.name]"
                    :disabled="isUpdating && col.isKey" type="text" class="form-control fill-parent-width">
                <datetime v-if="col.type == 'dateTime'" format="YYYY/MM/DD H:i:s" v-model="register[col.name]"
                    :placeholder="col.display" :readonly="isUpdating && col.isKey" class="fill-parent-width">
                </datetime>
                <select v-if="col.type == 'dropDown'" v-model="register[col.name]"
                    class="form-control fill-parent-width">
                    <option v-for="option in col.options" :value="option">{{ option }}</option>
                </select>
            </div>
            <div class="fixed-width">
                <button class="btn btn-outline-secondary" type="button" @click="emitSave()">Salvar</button>
                <button class="btn btn-outline-secondary" type="button" @click="emitCancel()">Cancelar</button>
            </div>
        </div>
    </div>
</template>

<script>
import datetime from 'vuejs-datetimepicker';

export default {
    components: {
        datetime
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
        emitSave() {
            this.$emit("onSave", this.register)
        },
        emitCancel() {
            this.$emit("onCancel")
        }
    },
    created() {
        this.isUpdating = Object.keys(this.register).length > 0
    }
}
</script>

<style scoped>
.fill-parent-width {
    width: 100%;
}
</style>