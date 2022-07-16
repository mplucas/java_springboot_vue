const columns = [
    { name: 'productID', display: 'Código do produto', isKey: true },
    { name: 'sellDate', display: 'Data de venda', isKey: true, type: 'dateTime' },
    { name: 'type', display: 'Tipo' },
    { name: 'sellPrice', display: 'Preço de venda' },
    { name: 'quantityMoved', display: 'Quantidade' }
]

export default columns