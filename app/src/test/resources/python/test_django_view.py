from django.shortcuts import render
from django.http import HttpResponse
from django.template import loader

def simple_render(request):
    # Simple render example
    return render(request, 'template.html', {'message': 'Hello'})

def template_loader(request):
    # Template loader example
    template = loader.get_template('template.html')
    context = {
        'message': 'Hello',
        'items': ['item1', 'item2', 'item3']
    }
    return HttpResponse(template.render(context, request))

def multiple_templates(request):
    # Example with conditional template selection
    if request.user.is_authenticated:
        template_name = 'authenticated.html'
    else:
        template_name = 'anonymous.html'

    context = {'user_status': 'logged_in' if request.user.is_authenticated else 'anonymous'}
    return render(request, template_name, context)

def unsafe_render(request):
    # Example with potential security issue
    template_name = request.GET.get('template', 'default.html')
    user_message = request.GET.get('message', '')
    return render(request, template_name, {'message': user_message})