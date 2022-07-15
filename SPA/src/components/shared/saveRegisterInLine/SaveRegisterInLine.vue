<template>
    <div>
        <div class="input-group input-group-sm mb-3">
            <input v-for="col in columns" :placeholder="col.display" v-model="register[col.name]" type="text"
                class="form-control">
            <button class="btn btn-outline-secondary" type="button" @click="emitSave">Salvar</button>
            <button class="btn btn-outline-secondary" type="button" @click="emitCancel">Cancelar</button>
        </div>
    </div>
</template>

<script>

export default {
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
    }
}
</script>

<style>
</style>