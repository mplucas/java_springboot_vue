<template>
    <div>
        <h2 class="centralizado">Consulta por tipo de produto</h2>
        <ul class="list-group list-group-horizontal">
            <li v-for="col in productTypeSummaryColumns" class="list-group-item fixed-width">
                {{ col.display }}
            </li>
        </ul>
        <div v-for="productTypeSummary in productTypeSummarys">
            <line-stock-movement :register="productTypeSummary" :columns="productTypeSummaryColumns" :canEdit="false">
            </line-stock-movement>
        </div>
    </div>
</template>

<script>
import http from '../../http/index.js'
import productTypeSummaryColumns from '../../columns/productTypeSummaryColumns.js'
import GridLine from '../shared/gridLine/GridLine.vue'

export default {
    components: {
        "line-stock-movement": GridLine
    },
    data() {
        return {
            productTypeSummarys: [],
            productTypeSummaryColumns: productTypeSummaryColumns
        }
    },
    methods: {
        getAllProducts() {
            http.get('product/getProductTypeSummary')
                .then(response => {
                    this.productTypeSummarys = response.data
                })
                .catch(() => {
                    alert('Erro ao buscar consulta')
                })
        }
    },
    created() {
        this.getAllProducts()
    }
}
</script>

<style>
</style>