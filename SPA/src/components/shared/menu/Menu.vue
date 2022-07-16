<template>
    <nav class="navbar navbar-expand-lg bg-light">
        <div class="container-fluid">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li v-for="route in routes" class="nav-item">
                    <router-link :to="route.path ? route.path : '/'" class="nav-link">{{
                            route.titulo
                    }}</router-link>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
export default {
    props: {
        routes: {
            type: Array,
            required: true
        }
    }
};
</script>

<style>
</style>