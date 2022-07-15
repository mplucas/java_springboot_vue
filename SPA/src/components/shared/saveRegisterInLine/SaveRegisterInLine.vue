<template>
    <div>
        <div class="input-group input-group-sm mb-3">
            <input v-for="col in columns" :placeholder="col.display" v-model="register[col.name]"
                :disabled="isUpdating && col.isKey" type="text" class="form-control fixed-width">
            <div class="fixed-width">
                <button class="btn btn-outline-secondary" type="button" @click="emitSave()">Salvar</button>
                <button class="btn btn-outline-secondary" type="button" @click="emitCancel()">Cancelar</button>
            </div>
        </div>
    </div>
</template>

<script>

export default {
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

<style>
</style>