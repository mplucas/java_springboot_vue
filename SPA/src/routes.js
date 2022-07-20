import Products from './components/products/Products.vue'
import StockMovements from './components/stockMovements/StockMovements.vue'
import ProductTypeSummary from './components/productTypeSummary/ProductTypeSummary.vue'

export const routes = [
    { path: "", component: Products, titulo: "Produtos" },
    { path: "/stockMovements", component: StockMovements, titulo: "Movimentação de estoque" },
    { path: "/productTypeSummary", component: ProductTypeSummary, titulo: "Consulta por tipo de produto" },
]