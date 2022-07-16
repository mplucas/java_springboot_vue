import Products from './components/products/Products.vue'
import StockMovements from './components/stockMovements/StockMovements.vue'

export const routes = [
    { path: "", component: Products, titulo: "Produtos" },
    { path: "/stockMovements", component: StockMovements, titulo: "Movimentação de estoque" }
]