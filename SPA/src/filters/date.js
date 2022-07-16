export default (value) => {
    if (!value || !(value instanceof Date)) return value
    return value.getFullYear().toString() + '/'
        + (value.getMonth() + 1).toString().padStart(2, '0') + '/'
        + value.getDate().toString().padStart(2, '0') + ' '
        + value.getHours().toString().padStart(2, '0') + ':'
        + value.getMinutes().toString().padStart(2, '0') + ':'
        + value.getSeconds().toString().padStart(2, '0')
}