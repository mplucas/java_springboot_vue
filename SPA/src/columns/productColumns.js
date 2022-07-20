const columns = [
    { name: 'productID', display: 'Código', preventEdit: true },
    { name: 'description', display: 'Descrição' },
    { name: 'type', display: 'Tipo', type: 'dropDown', options: ['Eletrônico', 'Eletrodoméstico', 'Móvel'] },
    { name: 'buyPrice', display: 'Preço de compra' },
    { name: 'stockQuantity', display: 'Quantidade', preventEdit: true }
]

export default columns