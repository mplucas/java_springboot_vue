<template>
    <div>
        <h2 class="centralizado">Produtos</h2>
        <add-product @onAdd="saveProduct($event)" :columns="productColumns"></add-product>
        <ul class="list-group list-group-horizontal">
            <li v-for="col in productColumns" class="list-group-item fixed-width">
                {{ col.display }}
            </li>
        </ul>
        <ul v-for="product in products" class="list-group list-group-horizontal list-group-flush">
            <li v-for="col in productColumns" class="list-group-item fixed-width">
                {{ product[col.name] }}
            </li>
        </ul>
    </div>
</template>

<script>
import http from '../../http/index.js'
import AddRegisterButton from '../shared/addRegisterButton/AddRegisterButton.vue'
import productColumns from '../../columns/productColumns.js'

export default {
    components: {
        "add-product": AddRegisterButton
    },
    data() {
        return {
            products: [],
            productColumns: productColumns
        }
    },
    methods: {
        getAllProducts() {
            http
                .get('product/getAll')
                .then(response => {
                    this.products = response.data
                })
                .catch(() => {
                    alert('Erro ao buscar produtos')
                })
        },
        saveProduct(product) {
            http
                .post('product/save', product)
                .then(() => {
                    alert('Registro salvo')
                    this.getAllProducts()
                })
                .catch(() => {
                    alert('Erro ao salvar')
                })
        }
    },
    created() {
        this.getAllProducts()
    }
}
</script>

<style scoped>
.centralizado {
    text-align: center;
}
.fixed-width{
    width: 250px;
}
</style>