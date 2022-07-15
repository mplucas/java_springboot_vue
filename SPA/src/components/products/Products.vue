<template>
    <div>
        <h2 class="centralizado">Produtos</h2>
        <ul class="list-group">
            <li class="list-group-item">An item</li>
            <li class="list-group-item">A second item</li>
            <li class="list-group-item">A third item</li>
            <li class="list-group-item">A fourth item</li>
            <li class="list-group-item">And a fifth one</li>
        </ul>
    </div>
</template>

<script>
import http from '../../http/index.ts'

export default {
    data() {
        return {
            products: []
        }
    },

    created() {
        http
            .get('product/getAll')
            .then(response => {
                this.products = response.data
                console.log(this.products)
            })
    }
}
</script>

<style scoped>
.centralizado {
    text-align: center;
}
</style>