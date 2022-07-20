<template>
    <div>
        <h2 class="centralizado">Movimentação de Estoque</h2>
        <add-stock-movement @onAdd="saveStockMovement($event)" :columns="stockMovementColumns"></add-stock-movement>
        <ul class="list-group list-group-horizontal">
            <li v-for="col in stockMovementColumns" class="list-group-item fixed-width">
                {{ col.display }}
            </li>
        </ul>
        <div v-for="stockMovement in stockMovements">
            <line-stock-movement :register="stockMovement" :columns="stockMovementColumns" :canEdit="false">
            </line-stock-movement>
        </div>
    </div>
</template>

<script>
import http from '../../http/index.js'
import stockMovementColumns from '../../columns/stockMovementColumns.js'
import AddRegisterButton from '../shared/addRegisterButton/AddRegisterButton.vue'
import EditRegisterInLine from "../shared/editRegisterInLine/EditRegisterInLine.vue"
import GridLine from '../shared/gridLine/GridLine.vue'

export default {
    components: {
        "add-stock-movement": AddRegisterButton,
        "update-stock-movement": EditRegisterInLine,
        "line-stock-movement": GridLine
    },
    data() {
        return {
            stockMovements: [],
            stockMovementColumns: stockMovementColumns
        }
    },
    methods: {
        getAllStockMovements() {
            http.get('stockMovement/getAll')
                .then(response => {
                    this.stockMovements = response.data
                })
                .catch(() => {
                    alert('Erro ao buscar movimentações')
                })
        },
        saveStockMovement(stockMovement) {
            http.post('stockMovement/save', stockMovement)
                .then(() => {
                    alert('Registro salvo')
                })
                .catch((error) => {
                    alert('Erro ao salvar: ' + error.response.data.message)
                })
                .finally(() => {
                    this.getAllStockMovements()
                })
        },
        deleteStockMovement(stockMovement) {
            http.delete('stockMovement/delete', { data: stockMovement })
                .then(() => {
                    alert('Registro excluído')
                    this.getAllStockMovements()
                })
                .catch(() => {
                    alert('Erro ao excluir')
                })
        },
        askDeleteStockMovement(stockMovement) {
            const confirmed = confirm('Voce realmente deseja apagar a movimentação ' + stockMovement.stockMovementID + '?')
            if (confirmed) {
                this.deleteStockMovement(stockMovement)
            }
        }
    },
    created() {
        this.getAllStockMovements()
    }
}
</script>

<style>
</style>