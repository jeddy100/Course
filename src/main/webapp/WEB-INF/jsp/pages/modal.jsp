<form:form action="/admin/deletePenalite" method="post">
    <input type="hidden" value="<%=etape.getId()%>" name="id">
    <a href="#" class="btn btn-danger " data-bs-toggle="modal" data-bs-target="#modal-small">
        <span class="ti ti-trash"></span>
    </a>
    <div class="modal modal-blur fade" id="modal-small" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog modal-sm modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <div class="modal-title">Are you sure?</div>
                    <p class="text-wrap ">Voulez vous vraiment effacer la pénalité pour l'équipe <%=etape.getEquipe().getNomEquipe()%> ? </p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-link link-secondary me-auto" data-bs-dismiss="modal">Non</button>
                    <button type="submit" class="btn btn-danger" data-bs-dismiss="modal">Oui</button>
                </div>
            </div>
        </div>
    </div>
</form:form>