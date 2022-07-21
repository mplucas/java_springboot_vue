<template>
    <div>
        <h2 class="centralizado">Consulta por tipo de produto</h2>
        <ul class="list-group list-group-horizontal">
            <li v-for="col in productBalanceSummaryColumns" class="list-group-item fixed-width">
                {{ col.display }}
            </li>
        </ul>
        <div v-for="productBalanceSummary in productBalanceSummarys">
            <line-stock-movement :register="productBalanceSummary" :columns="productBalanceSummaryColumns" :canEdit="false">
            </line-stock-movement>
        </div>
    </div>
</template>

<script>
import http from '../../http/index.js'
import productBalanceSummaryColumns from '../../columns/productBalanceSummaryColumns.js'
import GridLine from '../shared/gridLine/GridLine.vue'

export default {
    components: {
        "line-stock-movement": GridLine
    },
    data() {
        return {
            productBalanceSummarys: [],
            productBalanceSummaryColumns: productBalanceSummaryColumns
        }
    },
    methods: {
        getAllProducts() {
            http.get('product/getProductBalanceSummary')
                .then(response => {
                    this.productBalanceSummarys = response.data
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